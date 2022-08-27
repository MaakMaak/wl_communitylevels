If using the Community Edition, additionally execute the following commands on the command line manually:

npm run pnpm-install in the root of your project (next to the pnpm-workspace.yaml) to install all JavaScript dependencies.

npm run build:dev:watch in the helloscout.ui.html module to start the JavaScript build and watcher.
The watcher keeps on running and will continuously update the JavaScript assets as you change your JavaScript source files (hot-code-replace).
