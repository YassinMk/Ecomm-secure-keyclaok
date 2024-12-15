# Ecom Angular Keycloak Integration

This guide outlines the steps required to integrate Keycloak with an Angular application using the `keycloak-angular` library.

---

## 1. Install Keycloak-Angular
First, install the `keycloak-angular` library to enable Keycloak integration in your Angular project. Follow the installation instructions provided at [Keycloak Angular on npm](https://www.npmjs.com/package/keycloak-angular).

### Key Steps:
- Add the required dependencies to your project:
  ```bash
  npm install keycloak-angular keycloak-js
  ```
- Import `KeycloakService` and configure it in your application’s root module (`AppModule`).

---

## 2. Add the Silent Check SSO File
The `silent-check-sso.html` file is required for the silent login feature to function. Place this file in the **public folder** of your Angular project to make it accessible.

### Update `angular.json` to Include Public Folder
Ensure the file is properly served by adding the following configuration in the `angular.json` file:

```json
"assets": [
  {
    "glob": "**/*",
    "input": "public",
    "output": "/assets"
  }
]
```

This configuration ensures that files in the `public` folder are included in the Angular build output under the `/assets` directory.

---

## 3. Handle Roles in Routes
To manage route protection effectively, ensure that the route guard works with the `["roles"]` key. This key is essential because the route data is defined using an **index signature**.

### Example:
When defining route data for role-based access, use:
```typescript
const requiredRoles = route.data['roles'];
```

### Protecting Routes:
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

In your custom guard, verify the user's roles using the `requiredRoles` value.

---

## 4. Configure Redirect URI and Web Origins in Keycloak
For proper redirection and CORS handling, ensure the following configurations are set in the Keycloak admin interface:

1. **Redirect URIs**:
   - Add a valid redirect URI that points to your Angular app (e.g., `http://localhost:4200/*`).
2. **Web Origins**:
   - Define the allowed origins to include your Angular app's domain (e.g., `http://localhost:4200`).

These configurations ensure that Keycloak can communicate securely with your Angular application.

---

By following these steps, your Angular project will be successfully integrated with Keycloak for authentication and authorization.