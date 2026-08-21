import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import './index.css'

import RegistrationPage from "./Pages/RegistrationPage.jsx";
import LoginPage from "./Pages/LoginPage.jsx";
import SolarWatchPage from "./Pages/SolarWatchPage.jsx";

const router = createBrowserRouter([
    {
      path: "/registration",
      element: <RegistrationPage />
    },
    {
        path: "/login",
        element: <LoginPage />
    },
    {
        path: "/solar-watch",
        element: <SolarWatchPage />
    },
])

createRoot(document.getElementById("root")).render(
    <StrictMode>
            <RouterProvider router={router} />
    </StrictMode>,
);
