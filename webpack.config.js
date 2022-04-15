const MiniCssExtractPlugin = require('mini-css-extract-plugin');

module.exports = {
	mode: "development",
	entry: "./src/main/resources/static/js/app.js",
	output: {
		path: `${__dirname}/target/classes/static`,
		filename: "js/app.js"
	},
	plugins: [new MiniCssExtractPlugin({
		filename: "css/app.css",
	})],
	module: {
		rules: [
			{
				test: /\.css$/,
				use: [
					MiniCssExtractPlugin.loader,
					"css-loader",
					"postcss-loader"
				],
			}
		],
	},
}