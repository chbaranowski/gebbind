package geb.bind

import geb.Browser

/**
 * The bind type is the basis of each bind class.
 */
abstract class Bind {

    /**
     * Start a new bind instance.
     *
     * @param browser the geb browser instance not null.
     * @param bindClass the bind class to start.
     * @return instance of the bind class.
     */
    static Bind start(Browser browser, Class<Bind> bindClass) {
        Bind bind = bindClass.newInstance()
        bind.browser = browser
        bind.start()
        return bind
    }

    /**
     * Creates a new input text element.
     *
     * @param selector the geb selector as closure.
     * @return the input text instance.
     */
    static InputText inputText(Closure selector) {
        new InputText(selector: selector)
    }

    /**
     * Create a new button element.
     *
     * @param options some options to configure the button element.
     * @return the new button instance.
     */
    static Button button(Map options) {
        new Button().configure(options)
    }

    /**
     * Create a button with only a geb selector.
     *
     * @param selector the geb selector as closure.
     * @return the new button instance.
     */
    static Button button(Closure selector) {
        button(selector: selector)
    }

    /**
     * Create a new text web element.
     *
     * @param selector the geb selector.
     * @return the new text element.
     */
    static Text text(Closure selector) {
        new Text(selector: selector)
    }

    /**
     * Create a new list of web elements.
     *
     * @param selector the selector for one or more elements.
     * @return the new list element.
     */
    static List list(Closure selector) {
        new List(selector: selector)
    }

    static Table table(Closure rows) {
        table(rows: rows)
    }

    static Table table(Map options) {
        new Table().configure(options)
    }

    /**
     * The actual used geb browser instance.
     */
    Browser browser

    /**
     * Template method, the method is called on start.
     */
    def start() {}

    /**
     * DSL method to enter some text into a web element.
     * @param text the text to insert
     * @return the binding class to select web element
     */
    BindTextInput enter(String text) {
        new BindTextInput(text: text)
    }

    /**
     * DSL method to click on click able web element like a button.
     * @param clickable the click able web element.
     */
    void click(Clickable clickable) {
        clickable.click()
    }

    def methodMissing(String name, args) {
        browser."$name"(* args)
    }

    def propertyMissing(String name) {
        browser."$name"
    }

    def propertyMissing(String name, value) {
        browser."$name" = value
    }

    def call(Closure callable) {
        callable.delegate = this
        callable.resolveStrategy = Closure.DELEGATE_FIRST
        callable()
        true
    }

}
