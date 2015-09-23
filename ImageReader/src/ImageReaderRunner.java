// ImagaReaderRunner.java
import imagereader.Runner;

public final class ImageReaderRunner {
    private ImageReaderRunner() {
    }
    public static void main(String[] args) {
        ImplementImageIO imageioer = new ImplementImageIO();
        ImplementImageProcesser processor = new ImplementImageProcesser();
        Runner.run(imageioer, processor);
    }
}
