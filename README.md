# GebBind a Simple Geb Extension

GebBind is a small geb extension for a static type binding.
Here a simple example for a web binding class and a spock test.

The binding class to bind the spock test to a simple web site:

    class SampleBind extends Bind {

        def start() {
            go '/index.html'
            waitFor { title == 'GebBind-Test' }
        }

        InputText nameTextInput = inputText {
            $('form').find('input', name: 'firstname')
        }

        Button someButton = button onClick: {
            $('form').find('input', name: 'someButton').click()
            waitFor {
                infoText == 'some button clicked'
            }
        }

        Text infoText = text {
            $('#info-text')
        }

    }

here the spock test with full code completion support in a ide like Intellij:

    def "click on button element and enter some text"() {
        given:
        SampleBind sample = start SampleBind
        when:
        sample {
            click someButton
            enter 'some name' into nameTextInput
        }
        then:
        sample.infoText == 'some button clicked'
        sample.nameTextInput.value == 'some name'
    }

