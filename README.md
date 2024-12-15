# Ecom Secure Application with Keycloak Integration

This repository demonstrates how to secure a complete e-commerce application, including both the Angular frontend and the backend, using Keycloak for authentication and authorization. Below is a step-by-step guide to integrate Keycloak with both the frontend and backend services.

---

## Table of Contents

- [Overview](#overview)
- [Frontend (Angular) Integration](#frontend-angular-integration)
  - [1. Install Keycloak-Angular](#1-install-keycloak-angular)
  - [2. Add Silent Check SSO File](#2-add-silent-check-sso-file)
  - [3. Handle Roles in Routes](#3-handle-roles-in-routes)
  - [4. Configure Redirect URI and Web Origins in Keycloak](#4-configure-redirect-uri-and-web-origins-in-keycloak)
- [Backend Integration](#backend-integration)
  - [1. Get Access Token Using Resource Owner Password Credentials](#1-get-access-token-using-resource-owner-password-credentials)
  - [2. Refresh Access Token](#2-refresh-access-token)
  - [3. Get Access Token Using Client Credentials Grant](#3-get-access-token-using-client-credentials-grant)
- [Communication Between Microservices](#communication-between-microservices)
- [Notes](#notes)
- [License](#license)

---

## Overview

This project uses **Keycloak** to provide secure authentication and authorization mechanisms for a full-stack e-commerce application. It covers:

1. Securing the Angular frontend with `keycloak-angular`.
2. Implementing secure token handling and communication in the backend.
3. Managing communication between microservices using token-based authentication.

---

## Frontend (Angular) Integration

### 1. Install Keycloak-Angular

To integrate Keycloak with the Angular application, install the `keycloak-angular` and `keycloak-js` libraries:

```bash
npm install keycloak-angular keycloak-js
````

Import KeycloakService and configure it in the root module (AppModule).

### 2. Add Silent Check SSO File

- Add the `silent-check-sso.html` file in the `public` folder of your Angular project to enable silent login.
- Update the `angular.json` file to ensure the `public` folder is included in the build output:

```json
"assets": [
  {
    "glob": "**/*",
    "input": "public",
    "output": "/assets"
  }
]
````
### 3. Handle Roles in Routes

Use `KeycloakAuthGuard` for route protection and define role-based access:

```typescript
{
  path: 'admin',
  component: AdminComponent,
  canActivate: [KeycloakAuthGuard],
  data: {
    roles: ['admin']
  }
}
```
### 4. Configure Redirect URI and Web Origins in Keycloak

In the Keycloak admin console:

- **Redirect URIs:** Add a valid redirect URI (e.g., `http://localhost:4200/*`).
- **Web Origins:** Include the app's domain (e.g., `http://localhost:4200`).

## Backend 
### 1. Get Access Token Using Resource Owner Password Credentials

Request an access token using a username and password:

```http
POST http://localhost:8080/realms/ecom-realm/protocol/openid-connect/token
Headers:
  Content-Type: application/x-www-form-urlencoded
Body:
  grant_type=password&client_id=ecom-front&username=user1&password=1234
```
### 2. Refresh Access Token

Refresh your access token before it expires:

```http
POST http://localhost:8080/realms/ecom-realm/protocol/openid-connect/token
Headers:
  Content-Type: application/x-www-form-urlencoded
Body:
  grant_type=refresh_token&client_id=ecom-front&refresh_token=<YOUR_REFRESH_TOKEN>
```

### 3. Get Access Token Using Client Credentials Grant

Authenticate using client credentials (no end-user required):

```http
POST http://localhost:8080/realms/ecom-realm/protocol/openid-connect/token
Headers:
  Content-Type: application/x-www-form-urlencoded
Body:
  grant_type=client_credentials&client_id=ecom-front&client_secret=<YOUR_CLIENT_SECRET>
```
### Communication Between Microservices

When using `openfeign` to facilitate microservices communication, implement an interceptor to add the access token to the request headers. This ensures all microservices requests are authenticated securely.

### Notes

- Replace `http://localhost:8080` with your production Keycloak server URL.
- Secure sensitive information such as client secrets and tokens to avoid exposure.
- Refer to the [Keycloak Documentation](https://www.keycloak.org/documentation.html) for more details.

