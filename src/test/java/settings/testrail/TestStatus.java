package settings.testrail;

public enum TestStatus {

    PASSED(1),
    BLOCKED(2),
    UNTESTED(3),
    RETEST(4),
    FAILED(5),
    AUTO_TEST_FAILED(6),
    AUTO_TEST_PASSED(7);

    private final int status;

    TestStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

}
