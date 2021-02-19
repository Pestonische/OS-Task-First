package by.fpmi.os.main;

public class StreamStringProcessorTest extends StringProcessorTest{
    @Override
    protected StringProcessor getProcessor() {
        return new StreamStringProcessor();
    }
}
