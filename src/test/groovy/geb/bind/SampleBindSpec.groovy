package geb.bind

import spock.lang.*

import geb.bind.spock.GebBindSpec
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.webapp.WebAppContext

class SampleBindSpec extends GebBindSpec {

    static port = 9090

    @Shared
    Server server

    def setupSpec() {
        server = new Server(port)
        WebAppContext context   = new WebAppContext()
        context.resourceBase    = "src/test/webapp/"
        context.contextPath     = "/"
        server.setHandler(context)
        server.start()
    }

    def cleanupSpec() {
        server.stop()
    }

    def setup() {
        browser.baseUrl = "http://localhost:$port"
    }

    def "start simple bind instance"() {
        when:
        SimpleBind bind = start SimpleBind
        then:
        bind.browser != null
        bind.started == true
    }

    def "insert text into InputText element"() {
        given:
        SampleBind sample = start SampleBind
        when:
        sample {
            enter 'Some first name' into nameTextInput
        }
        then:
        sample.nameTextInput.value == 'Some first name'
    }

    def "click on submit button element"() {
        given:
        SampleBind sample = start SampleBind
        when:
        sample {
            enter 'some name' into nameTextInput
            assert nameTextInput.value == 'some name'
            click resetButton
        }
        then:
        assert sample.nameTextInput.value == ''
    }

    def "click on button element"() {
        given:
        SampleBind sample = start SampleBind
        when:
        sample {
            click someButton
        }
        then:
        sample.infoText == 'some button clicked'
    }

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

    def "verify list items on site"() {
        given:
        SampleBind sample = start SampleBind
        expect:
        sample.ul.size() == 2
        sample.ul[0].text() == 'First Element'
        sample.ul[1].text() == 'Second Element'
    }

}
