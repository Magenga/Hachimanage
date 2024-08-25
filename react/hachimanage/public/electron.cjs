const { app, BrowserWindow } = require('electron');
const path = require('path');
const os = require('os');  // 引入 os 模块来检测平台

let mainWindow;

function createWindow() {
    mainWindow = new BrowserWindow({
        width: 800,
        height: 600,
        autoHideMenuBar: true,
        webPreferences: {
            preload: path.join(__dirname, 'preload.js'),
            contextIsolation: true,
            backgroundThrottling: false

        },
    });

    mainWindow.loadURL(
        process.env.ELECTRON_START_URL || 'http://localhost:5173'
    );

    mainWindow.webContents.on('did-finish-load', () => {
        mainWindow.webContents.focus();
    });

    mainWindow.on('closed', function () {
        mainWindow = null;
    });

    mainWindow.webContents.on('did-finish-load', () => {
        setTimeout(() => {
            mainWindow.webContents.focus();
        }, 100);  // 延迟100ms
    });
}

app.on('ready', () => {
    if (os.platform() === 'darwin') {
        app.setSecureKeyboardEntryEnabled(true);
    }
    createWindow();
});

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

app.disableHardwareAcceleration();

