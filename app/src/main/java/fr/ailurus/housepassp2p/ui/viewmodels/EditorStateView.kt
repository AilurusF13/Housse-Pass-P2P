package fr.ailurus.housepassp2p.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import fr.ailurus.housepassp2p.data.entities.Entry
import fr.ailurus.housepassp2p.data.entities.EntrySummary
import fr.ailurus.housepassp2p.data.entities.GroupSummary
import fr.ailurus.housepassp2p.data.repository.RepositoryManager
import fr.ailurus.housepassp2p.data.uidatas.EntryCard
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

/**
 *  Manage and store the state of the editor withing the given scope.
 *
 *  Use the Repository Manager in order to save/delete entries in the database
 *
 *  @param scope The scope to use for coroutines. Should be the scope of the parent composable. Such as the VaultViewModel's
 *  @param repositoryManager The repository manager to use for database operations.
 *  @param groups The list of groups to use for the editor. Necessary in order to display the group names in the editor's selection.
 *                needs to have at least one element (self), TODO can a security can be implemented
 *  @param isEditorOpen The state flow to use to open/close the editor.
 */
class EditorStateView(
    private val scope: CoroutineScope,
    private val repositoryManager: RepositoryManager,
    val getGroups: () -> List<GroupSummary>,
    private val isEditorOpen: MutableStateFlow<Boolean>
) {
    var editorState  by mutableStateOf<EntryCard?>(null)
        private set

    val groups: List<GroupSummary> get() = getGroups()

    var oldState = EntryCard(0,  "", "", GroupSummary(0, ""))

    /**
     * I don't like it here because using String "make us loose the track of the previous string"
     *
     * the password is never loaded and needs to be retyped
     */
    var password by mutableStateOf("")
        private set

    fun initialize(entry: EntryCard?){
        editorState = entry
            ?: EntryCard(
                id = 0,
                site = "",
                login = "",
                group = groups.firstOrNull() ?: GroupSummary(groupId = -1, name = "Unknown")
            )

        editorState?.let { oldState = it.copy() } // should always triggered
        password = ""
        isEditorOpen.value = true
        Log.d("EDITOR_STATE", "Editor state initialized")
    }

    fun updateSite(v: String) { editorState = editorState?.copy(site = v) }
    fun updateLogin(v: String) { editorState = editorState?.copy(login = v) }
    fun updateGroup(v: GroupSummary) { editorState = editorState?.copy(group = v) }
    fun updatePassword(v: String) { password = v }

    fun onSave(){
        if (oldState == editorState){
            isEditorOpen.value = false
            return
        }

        // TODO no restrictions on save yet
        val currentState = editorState

        if (currentState == null){
            Log.e("EDITOR_STATE", "Editor state is null")
            return
        }

        scope.launch {
            try {
                val entry = Entry (
                    id = currentState.id,
                    site = currentState.site,
                    login = currentState.login,
                    groupId = currentState.group.groupId,
                    password = password.toByteArray(),
                )
                repositoryManager.saveEntry(entry)
            } catch (e: Exception){
                Log.e("REPO_MANAGER", "Failed to save entry : ${e.message}")
            }
        }
        isEditorOpen.value = false
    }

    fun onDelete(){
        val currentState = editorState
        if (currentState == null){
            Log.e("EDITOR_STATE", "Editor state is null")
            return
        }

        scope.launch {
            try {
                repositoryManager.deleteEntry(EntrySummary(
                    id = currentState.id,
                    site = currentState.site,
                    login = currentState.login,
                    groupId = currentState.group.groupId
                    // ATTENTION need to make sure there are no null groups in the editor
                ))
            } catch (e: Exception){
                Log.e("REPO_MANAGER", "Failed to delete entry : ${e.message}")
            }
            editorState = null
        }
        isEditorOpen.value = false
    }
}