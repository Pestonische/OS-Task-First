package by.fpmi.os.main;

public class ThreadStringProcessorTest extends StringProcessorTest {
    @Override
    protected StringProcessor getProcessor() {
        return new ThreadsStringProcessor();
    }
}
