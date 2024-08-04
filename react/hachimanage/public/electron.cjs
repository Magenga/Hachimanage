const { app, BrowserWindow } = require('electron');
const path = require('path');

let mainWindow;

function createWindow() {
    const mainWindow = new BrowserWindow({
        width: 800,
        height: 600,
        webPreferences: {
            preload: path.join(__dirname, 'preload.js'),
            contextIsolation: true,
        },
    });

    mainWindow.loadURL(
        process.env.ELECTRON_START_URL || 'http://localhost:5173'
    );

    mainWindow.on('closed', function () {
        mainWindow = null;
    });
}

app.on('ready', createWindow);

app.on('window-all-closed', function () {
    if (process.platform !== 'darwin') {
        app.quit();
    }
});

app.on('activate', function () {
    if (mainWindow === null) {
        createWindow();
    }
});

app.on('will-finish-launching', () => {
    app.setSecureKeyboardEntryEnabled(true);
});
