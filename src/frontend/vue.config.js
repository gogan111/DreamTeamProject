module.exports = {
    devServer: {
        port: 3000,
        proxy: {
            '^/rest': {
                target: 'http://18.193.157.50:8081',
                ws: true,
                changeOrigin: true
            },
        }
    }
}