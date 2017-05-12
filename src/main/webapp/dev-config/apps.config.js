/**
 * Created by apple on 16/6/8.
 */
const defaultIndexPage = "./dev-config/server/template.html";

module.exports = {

  //基本的应用配置信息
  apps: [
    //HelloWorld
    {
      id: "app",
      src: "./src/index.js",
      indexPage: defaultIndexPage,
      compiled: true
    }
  ],

  //开发入口配置
  devServer: {
    appEntrySrc: "./src/index.js", //当前待调试的APP的入口文件
    port: 3000 //监听的Server端口
  },

  //依赖项配置
  proxy: {
    //后端服务器地址 http://your.backend/
    "/api/*": "http://localhost:3001",
  }

};