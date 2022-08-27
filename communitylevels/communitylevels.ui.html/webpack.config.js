const baseConfig = require('@eclipse-scout/cli/scripts/webpack-defaults');

module.exports = (env, args) => {
  args.resDirArray = ['src/main/resources/WebContent', 'node_modules/@eclipse-scout/core/res'];
  const config = baseConfig(env, args);

  config.entry = {
    'communitylevels': './src/main/js/communitylevels.js',
    'login': './src/main/js/login.js',
    'logout': './src/main/js/logout.js',
    'communitylevels-theme': './src/main/js/communitylevels-theme.less',
    'communitylevels-theme-dark': './src/main/js/communitylevels-theme-dark.less'
  };

  return config;
};
