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
                name: "languageName",
                message: "Language name",
                default: this.appname
            }]);
        Object.assign(this.answers, await this.prompt([
            {
                type: "input",
                name: "packageName",
                message: "Package name",
                default: `com.strumenta.${this.answers.languageName.toLowerCase()}parser`
            }
        ]));
        this.answers['packageNameAsPath'] = this.answers.packageName.replace(/\./g, "/")
        //console.log("answers", this.answers);
    }

    async writing() {
        // Project configuration
        this.fs.copyTpl(
            this.templatePath('project-conf/gradle.properties'),
            this.destinationPath('gradle.properties'),
            { }
        );
        this.fs.copyTpl(
            this.templatePath('project-conf/build.gradle'),
            this.destinationPath('build.gradle'),
            this.answers
        );

        // Gradle wrapper
        const files = [
            "gradlew",
            "gradlew.bat",
            "gradle/wrapper/gradle-wrapper.properties"
        ];
        for (let i = 0; i < files.length; i++) {
            this.fs.copyTpl(
                this.templatePath(`gradle-wrapper/${files[i]}`),
                this.destinationPath(files[i]),
                this.props
            );
        }
        this.fs.copy(
            this.templatePath("gradle-wrapper/gradle/wrapper/gradle-wrapper.jar"),
            this.destinationPath("gradle/wrapper/gradle-wrapper.jar")
        );

        // Language definition
        this.fs.copy(
            this.templatePath("antlr/gitignore"),
            this.destinationPath("src/main/antlr/.gitignore")
        );
        this.fs.copyTpl(
            this.templatePath("antlr/MyLanguageLexer.g4"),
            this.destinationPath(`src/main/antlr/${this.answers.languageName}Lexer.g4`),
            this.answers
        );
        this.fs.copyTpl(
            this.templatePath("antlr/MyLanguageParser.g4"),
            this.destinationPath(`src/main/antlr/${this.answers.languageName}Parser.g4`),
            this.answers
        );

        // Kotlin code
        this.fs.copyTpl(
            this.templatePath("kotlin-code/ast/ast.kt"),
            this.destinationPath(`src/main/kotlin/${this.answers.packageNameAsPath}/ast/ast.kt`),
            this.answers
        );
        this.fs.copyTpl(
            this.templatePath("kotlin-code/parsetreetoast/conversions.kt"),
            this.destinationPath(`src/main/kotlin/${this.answers.packageNameAsPath}/parsetreetoast/conversions.kt`),
            this.answers
        );
        this.fs.copyTpl(
            this.templatePath(`kotlin-code/MyLanguageKolasuParser.kt`),
            this.destinationPath(`src/main/kotlin/${this.answers.packageNameAsPath}/${this.answers.languageName}KolasuParser.kt`),
            this.answers
        );

        // Kotlin test code
        this.fs.copyTpl(
            this.templatePath("kotlin-test-code/MyLanguageLexerTest.kt"),
            this.destinationPath(`src/test/kotlin/${this.answers.packageNameAsPath}/${this.answers.languageName}LexerTest.kt`),
            this.answers
        );
        this.fs.copyTpl(
            this.templatePath("kotlin-test-code/MyLanguageFirstStageParserTest.kt"),
            this.destinationPath(`src/test/kotlin/${this.answers.packageNameAsPath}/${this.answers.languageName}FirstStageParserTest.kt`),
            this.answers
        );
        this.fs.copyTpl(
            this.templatePath("kotlin-test-code/MyLanguageKolasuParserTest.kt"),
            this.destinationPath(`src/test/kotlin/${this.answers.packageNameAsPath}/${this.answers.languageName}KolasuParserTest.kt`),
            this.answers
        );
        this.fs.copyTpl(
            this.templatePath("kotlin-test-code/example1.hello"),
            this.destinationPath(`src/test/resources/example1.hello`),
            this.answers
        );

        // Git
        this.fs.copy(
            this.templatePath("git/gitignore"),
            this.destinationPath(`.gitignore`),
            this.answers
        );
    }
};