package geb.bind

class Button extends BindElement implements Clickable {

    Closure onClick = { Clickable elm ->
        elm.click()
    }

    void click() {
        onClick(new Clickable(){
            void click() {
                selector().click()
            }
        })
    }

    Button configure(Map options) {
        this.selector = options.selector
        if(options.onClick) {
            this.onClick = options.onClick
        }
        return this
    }

}

