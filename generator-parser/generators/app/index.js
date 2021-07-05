const Generator = require('yeoman-generator');

module.exports = class extends Generator {
    constructor(args, opts) {
        super(args, opts);
        // We could add options here
    }

    // initializing - Your initialization methods (checking current project state, getting configs, etc)
    // prompting - Where you prompt users for options (where you’d call this.prompt())
    // configuring - Saving configurations and configure the project (creating .editorconfig files and other metadata files)
    // default - If the method name doesn’t match a priority, it will be pushed to this group.
    // writing - Where you write the generator specific files (routes, controllers, etc)
    // conflicts - Where conflicts are handled (used internally)
    // install - Where installations are run (npm, bower)
    // end - Called last, cleanup, say good bye, etc

    async prompting() {
        this.answers = await this.prompt([
            {
                type: "input",
                name: "name",
                message: "Your project name",
                default: this.appname // Default to current folder name
            },
            {
                type: "confirm",
                name: "cool",
                message: "Would you like to enable the Cool feature?"
            }
        ]);
    }

    async writing() {
        this.fs.copyTpl(
            this.templatePath('gradle.properties'),
            this.destinationPath('gradle.properties'),
            { }
        );
        this.fs.copyTpl(
            this.templatePath('build.gradle'),
            this.destinationPath('build.gradle'),
            { }
        );
    }
};