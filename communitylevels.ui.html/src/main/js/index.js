export { default as myCustom } from './myCustomJs/myCustom';

import * as self from './index.js';
export default self;
window.communitylevels = Object.assign(window.communitylevels || {}, self);
