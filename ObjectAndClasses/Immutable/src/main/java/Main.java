public class Main {
    public final class cargo {
        private int mass;
        private int size;
        private String addres;
        private boolean flip;
        private String regNum;
        private boolean brittle;

        public cargo(int mass, int size, String addres, boolean flip, String regNum, boolean brittle) {
        }


        public final class Dimension implements Cloneable {
            private int width;
            private int height;
            private int lenght;

            public void findVolume() {
                int size = width * height * lenght;
            }


            public void currentDimension() {
                int width = 0;
                int height = 0;
                int lenght = 0;
                this.width = width;
                this.height = height;
                this.lenght = lenght;
                return width.clone();
                return height.clone();
                return lenght.clone();
            }
        }

        public void currentAddres() {
            String addres = "";
            this.addres = addres;
            return addres.clone();

        }

        public void currentMass() {
            int mass = 0;
            this.mass = mass;
            return mass.clone();

        }
    }
}
