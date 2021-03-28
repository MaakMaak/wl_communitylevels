import {RemoteApp} from '@eclipse-scout/core';

Object.assign({}, this); // workaround so that the imports are not unused
var app = new RemoteApp();
app.init();
