import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import './index.css'

import RegistrationPage from "./Pages/RegistrationPage.jsx";
import LoginPage from "./Pages/LoginPage.jsx";
import SolarWatchPage from "./Pages/SolarWatchPage.jsx";

import AuthProvider from "./Context/AuthProvider.jsx";
import GuestsOnly from "./Components/GuestsOnly/GuestsOnly.jsx";
import Protected from "./Components/Protected/Protected.jsx";

const router = createBrowserRouter([
    {
      path: "/registration",
        element: (
            <GuestsOnly>
                <RegistrationPage />
            </GuestsOnly>
        ),
    },
    {
        path: "/login",
        element: (
            <GuestsOnly>
                <LoginPage />
            </GuestsOnly>
        ),
    },
    {
        path: "/solar-watch",
        element: (
            <Protected>
                <SolarWatchPage />
            </Protected>
        ),
    },
])

createRoot(document.getElementById("root")).render(
    <StrictMode>
        <AuthProvider>
            <RouterProvider router={router} />
        </AuthProvider>
    </StrictMode>,
);
