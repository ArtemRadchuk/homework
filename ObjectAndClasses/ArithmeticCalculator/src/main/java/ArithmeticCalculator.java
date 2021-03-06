public class ArithmeticCalculator {
    public static int firstNum;
    public static int secondNum;

    public ArithmeticCalculator(int firstNum, int secondNum) {
        this.firstNum = firstNum;
        this.secondNum = secondNum;
    }

    public int getFirstNum() {
        return firstNum;
    }

    public void setFirstNum(int firstNum) {
        this.firstNum = firstNum;
    }

    public int getSecondNum() {
        return secondNum;
    }

    public void setSecondNum(int secondNum) {
        this.secondNum = secondNum;
    }

    public void calculate(Operation operation) {
        switch (operation) {
            case ADD:
                System.out.println(firstNum + secondNum);
                break;
            case SUBTRACT:
                System.out.println(firstNum - secondNum);
                break;
            case MULTIPLY:
                System.out.println(firstNum * secondNum);
                break;
        }
    }


}
