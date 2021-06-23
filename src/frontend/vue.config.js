
publicPath: process.env.NODE_ENV === 'production'
module.exports = {
    publicPath: process.env.NODE_ENV = '',
    devServer: {
        devServer: {
            proxy: {
                '^/rest': {
                    target: 'http://localhost:8081/project',
                    ws: true,
                    changeOrigin: true
                },
            }
        }
    }
}


/*
module.exports = {
    devServer: {
        port: 3000,
        proxy: {
            '^/rest': {
                target: 'http://localhost:8081',
                ws: true,
                changeOrigin: true
            },
        }
    }
}*/
