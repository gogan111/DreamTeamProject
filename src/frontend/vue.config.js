module.exports = {
    publicPath: process.env.NODE_ENV = '',
    devServer: {
        devServer: {
            proxy: {
                '^/rest': {
                    target: 'http://3.125.155.150:8081/project',
                    ws: true,
                    changeOrigin: true
                },
            }
        }
    }
}