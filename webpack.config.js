module.exports = {
	mode: "development",
	entry: {
		'bundle': `./src/main/resources/static/js/app.js`,
	},
	output: {
		path: `${__dirname}/target/classes/static/js`,
		filename: "app.js"
	},
}