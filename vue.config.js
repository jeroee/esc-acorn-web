const fs = require('fs');

module.exports = {
  chainWebpack: config => config.optimization.minimize(false),
  "transpileDependencies": [
    "vuetify"
  ],
  devServer: {
    host: 'localhost',
    port: 8080,
    https: {
      key: fs.readFileSync('key.pem'),
      cert: fs.readFileSync('certificate.pem'),
    },
  }
};
