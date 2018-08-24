var path = require('path') //node 路径
var webpack = require('webpack')
var htmlWebpackPlugin = require('html-webpack-plugin') //webpack 将编译的js注入到模板
var cleanWebpackPlugin = require('clean-webpack-plugin')
var ExtractTextPlugin = require('extract-text-webpack-plugin') //抽离css样式


function resolve (dir) {
  return path.join(__dirname, './', dir)
}

module.exports = {
  devtool: 'false', //eval-source-map  path.resolve(__dirname, './src/index.jsx'), //指定webpack打包的入口文件
  entry: {
    bundle : path.resolve(__dirname, './src/index.jsx'),               //已多次提及的唯一入口文件
    vendor: ['react','react-dom','react-router-dom','react-loadable','redux','react-redux','wangeditor','react-addons-css-transition-group'] //在此处配置
  },
  output: {
    path: path.resolve(__dirname, 'build'),//publicPath: /处理browerRoute子路由刷新页面404-绝对路径
    publicPath: "/",
    filename: 'bundle-[hash].js'
  },
	resolve:{
    extensions:['.js','.jsx'],
    alias: {
      src: resolve('src'),
      api: resolve('src/api'),
      actions: resolve('src/actions'),
      common: resolve('src/common'),
      components: resolve('src/components'),
      containers: resolve('src/containers'),
      constants: resolve('src/constants'),
      layouts: resolve('src/layouts'),
      reducers: resolve('src/reducers'),
      router: resolve('src/router'),
      store: resolve('src/store')
    }
  },
  module: {
	  rules: [
      {
        test: /\.(js|jsx)$/,
        loader: 'babel-loader',
        query: {
          presets: ['react', 'es2015', 'stage-0']
        },
        include: [resolve('src'), resolve('test')]
      },
      { test: /\.less$/, loader: ExtractTextPlugin.extract("css-loader!less-loader") },
      { test: /\.css$/, loader: ExtractTextPlugin.extract("css-loader") },
      {
        test: /\.(png|jpe?g|gif|svg)(\?.*)?$/,
        loader: 'url-loader',
        options: {
          limit: 10000,
        }
      },
      {
        test: /\.(mp4|webm|ogg|mp3|wav|flac|aac)(\?.*)?$/,
        loader: 'url-loader',
        options: {
          limit: 10000,
        }
      },
      {
        test: /\.(woff2?|eot|ttf|otf)(\?.*)?$/,
        loader: 'url-loader',
        options: {
          limit: 10000,
        }
      }
    ]
  },
  externals: {
    jquery: "jQuery"
  },
  plugins: [
    new ExtractTextPlugin('style.css'),
    new htmlWebpackPlugin({
      template: './src/index.html', //定义插件读取的模板文件是根目录下的index.html
      filename: 'index.html' //定义通过模板文件新生成的页面名称
    }),
    new cleanWebpackPlugin(
      ['build/*.js', 'build/*.html'],
      {
        root: __dirname, //指定插件根目录位置
        verbose: true,   //开启控制台根目录信息
        dry: false       //启用删除文件
      }
    ),
    new webpack.optimize.CommonsChunkPlugin({
        name: 'vendor',
        filename: 'vendor.bundle.js'
    })
  ],
	devServer: {
    proxy: {
      '/event': {
        target: 'https://reg.coolgua.net',
        changeOrigin: true,
        secure: false
      },
      '/area': {
        target: 'https://reg.coolgua.net',
        changeOrigin: true,
        secure: false
      },
      '/common/login/': {
        target: 'https://reg.coolgua.net',
        changeOrigin: true,
        secure: false
      },
      '/plugin': {
        target: 'https://reg.coolgua.net',
        changeOrigin: true,
        secure: false
      }
    },
    contentBase: path.join(__dirname, "./src"),//本地服务器所加载的页面所在的目录
    historyApiFallback: true,//不跳转
    inline: true//实时刷新
  },

}
