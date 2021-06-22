module.exports = {
    devServer: {
        port: 3000,
        proxy: {
            '^/rest': {
                target: 'http://localhost:9000',
                ws: true,
                changeOrigin: true
            },
        }
    }
}