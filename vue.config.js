const fs = require('fs');

module.exports = {
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
