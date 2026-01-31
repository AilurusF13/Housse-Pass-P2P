# House Pass P2P - Password Manager

A decentralized and anonymous password manager for you and your family to share on Android.

## Concept

The vast majority of password managers are either cloud-based or fully local. This project focuses on two unique pillars:

- Zero Account & Identity: No email, no central identification, and no tracking. You are anonymous by design.

- Air-Gapped Spirit: Your data never touches the internet. Even encrypted, it stays within your private network.

- LAN Group Sharing: The application allows you to easily share password groups with other users in your home, using exclusively the Local Area Network (LAN).

The application is built to run on the Kotlin JVM, prioritizing security.

### Technologies

- Mobile: Kotlin + Jetpack Compose for a modern UI, Android Keystore System, biometrie, password generation and completion.

- Database: SQLCipher for transparent and robust 256-bit AES encryption of the local database.

- Crypto: Custom security layer for P2P key exchange and data integrity.

- Multi-platform: Kotlin (Android) and JavaScript/TypeScript (for the upcoming browser extension).

## Project Tree
```
|-- data/       # Entities, DAOs and Database configuration (SQLCipher)
|-- network/    # UDP Discovery and TCP Sync protocols
|-- crypto/     # Encryption logic and Key management
|-- ui/         # UI logic:
    |-- setup   # Initial database & password creation
    |-- login   # Vault access and reset options
    |-- main    # Password list and search
    |-- group   # Group management and sharing
```

## Prototype https://github.com/AilurusF13/VaultFamily

An early prototype is available with the following functional features:

- Setup Page: Handles initial vault creation and master password configuration.

- Login Page: Secure access to the database with an option to reset/wipe the data.

- Main Dashboard:

  - Search & Filters: Fully functional search bar and chip filters for navigating between multiple groups, when groups are manually enabled in source code, the chips are proven to work as intended.

  - P2P Sync Icon: Active discovery of other instances on the LAN via broadcast messages, only sends a message to the discovered inistance.

  - Vault CRUD: Adding, editing, and deleting entries (Website, Login, Password, Group) is fully implemented.

This project is an open-source initiative focused on local-first security and privacy. Currently on GPL liecnce.

# credits 
- SQLcipher for the databas encryption https://github.com/sqlcipher/sqlcipher
## Technicity