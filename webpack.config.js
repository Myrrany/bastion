const webpack = require('webpack');
const path = require('path');

module.exports = {
    context: path.join(__dirname, '.'),
    entry: [
        'react-hot-loader/patch',
        './src/main/js/app.js',
    ],
    mode: 'development',
    module: {
        rules: [
            {
                test: /\.(js|jsx)$/,
                exclude: /node_modules/,
                use: [{
                    loader: 'babel-loader',
                    options: {
                        presets: ["@babel/preset-react",
                            // "@babel/preset-react",
                            // {
                            //     "useBuiltIns": "entry"
                            // },
                        ]
                    }
                }],
            },
            {
                test: /\.css$/,
                loader: 'style-loader!css-loader',
            },
            {
                test: /\.(eot|svg|ttf|woff|woff2)$/,
                loader: 'file-loader',
            },
            {
                test: /\.less$/,
                loaders: ['style-loader', 'css-loader', 'less-loader'],
            },
            {
                test: /\.(jpg|jpeg|png|gif)$/,
                loader: 'file-loader',
            },
            {
                test: /\.svg$/,
                loader: 'svg-loader',
            },
        ],
    },
    resolve: {
        extensions: ['*', '.js', '.jsx'],
    },
    output: {
        path: `${__dirname}/src/main/resources/static/built`,
        filename: 'bundle.js',
    },
    plugins: [
        new webpack.HotModuleReplacementPlugin(),
    ],
    devtool: 'source-map',
    devServer: {
        proxy: {
            '/api/**': {
                target: 'http://localhost:8080/',
                pathRewrite: { '^/api': '' },
                secure: false,
                logLevel: 'debug'
            },
        },
        disableHostCheck: true,
        host: '0.0.0.0',
        port: 8000,
        contentBase: './src/main/resources/static',
        historyApiFallback: true,
        hot: true,
        headers: {
            "Access-Control-Allow-Origin": "http://localhost:8080",
            "Access-Control-Allow-Credentials": "true",
            "Access-Control-Allow-Headers": "Content-Type, Authorization, x-id, Content-Length, X-Requested-With",
            "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS"
        }
    },
};