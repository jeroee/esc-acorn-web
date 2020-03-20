const fs = require('fs')

module.exports = {
  "transpileDependencies": [
    "vuetify"
  ],
  devServer: {
    https: {
      key: fs.readFileSync('key.pem'),
      cert: fs.readFileSync('certificate.pem'),
    },
    public: 'https://localhost:8080/'
}
}
