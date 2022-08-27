import {RemoteApp} from '@eclipse-scout/core';
import * as communitylevels from './index';

Object.assign({}, communitylevels); // Use import so that it is not marked as unused

new RemoteApp().init();
