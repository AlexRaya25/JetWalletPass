# JetWalletPass - Prueba para usar la Wallet API de Google

## Descripción

**JetWalletPass** sirve como prueba para la integración de la **API de Google Wallet** utilizando la funcionalidad de **pases genéricos**. Esta aplicación permite crear y agregar pases digitales a **Google Wallet** a través de un proceso sencillo utilizando **JSON Web Tokens (JWT)**.

### ¿Qué hace la aplicación?

La aplicación permite la generación y visualización de un pase digital utilizando la API de Google Wallet. Utiliza el **Wallet Pass Builder** para crear un pase genérico y luego lo convierte en un **JWT** firmado utilizando el algoritmo **RS256**.

El flujo general es el siguiente:
1. **Generación de pase**: Utiliza el generador de pases de Google para crear un pase genérico (como una tarjeta de crédito, ticket de transporte, etc.).
2. **Conversión a JWT**: Convierte el pase generado en un **JWT** firmado, que es necesario para agregarlo a Google Wallet.
3. **Visualización y agregar pase**: Muestra el pase generado en la interfaz de usuario y permite agregarlo a **Google Wallet** a través de un botón de integración.

### Funcionalidades
- **Generación de Pase**: Crea un pase con la API de Google Wallet y convierte los datos en un JWT.
- **Interfaz de Usuario**: Muestra una tarjeta con la información del pase (logo, título, QR code) y un botón para agregar el pase a Google Wallet.
- **Estado del Pase**: El estado de la integración se gestiona con **StateFlow** de Jetpack, lo que permite una UI reactiva.
- **Flujo de Adición a Google Wallet**: Una vez que el pase se genera, el usuario puede agregarlo a su Google Wallet mediante un botón integrado.

### Requisitos

- **Android Studio** para el desarrollo y pruebas.
- **Cuenta de desarrollador en Google** para crear el pase usando la Wallet API.
- **JWT.io** para crear el JWT necesario para la integración del pase.

### Recursos

- [Generador de Pases Genéricos de Google](https://developers.google.com/wallet/generic/resources/pass-builder?hl=es-419)
- [JWT.io](https://jwt.io/)

| **Pantalla Principal** | **Añadiendo al Wallet** | **Pase Añadido al Wallet** |
|--------------------|---------------------|------------------------|
| <img src="https://github.com/user-attachments/assets/89073081-9691-4126-bf3d-097876dcf174" alt="Pantalla Principal" width="180" height="400"/> | <img src="https://github.com/user-attachments/assets/0a3919fc-0274-4e76-9172-8ea0357ea0a8" alt="Añadiendo Pase a Google Wallet" width="180" height="400"/> | <img src="https://github.com/user-attachments/assets/8c29468b-5d07-4510-b907-677f745c987a" alt="Pase Añadido al Wallet" width="180" height="400"/> |

## Instalación

1. Clona este repositorio:
   ```bash
   git clone https://github.com/AlexRaya25/jetwalletpass.git

2. Abre el proyecto en Android Studio.

3. Asegúrate de tener configuradas las dependencias necesarias en tu build.gradle.
