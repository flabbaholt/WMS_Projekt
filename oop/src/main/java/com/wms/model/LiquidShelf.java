package com.wms.model;

public class LiquidShelf extends Shelf {
    
        private String liquidType;
        private String liquidCapacity;
        private String liquidUsedCapacity;
    
        public LiquidShelf(String shIde, String shCat, String shCap, String ocCap, String shTyp, String shCon, String liqTyp, String liqCap, String liqOccCap) {
            super(shIde, shCat, shCap, ocCap, shTyp, shCon);
            this.liquidType = liqTyp;
            this.liquidCapacity = liqCap;
            this.liquidUsedCapacity = liqOccCap;
        }
    
        public LiquidShelf(String[] data) {
            super(data);
            this.liquidType = data[6];
            this.liquidCapacity = data[7];
            this.liquidUsedCapacity = data[8];
        }
    
        public String getLiquidType() {
            return liquidType;
        }
    
        public void setLiquidType(String liquidType) {
            this.liquidType = liquidType;
        }
    
        public String getLiquidCapacity() {
            return liquidCapacity;
        }
    
        public void setLiquidCapacity(String liquidCapacity) {
            this.liquidCapacity = liquidCapacity;
        }
    
        public String getLiquidUsedCapacity() {
            return liquidUsedCapacity;
        }
    
        public void setLiquidUsedCapacity(String liquidOccupiedCapacity) {
            this.liquidUsedCapacity = liquidOccupiedCapacity;
        }
    
        @Override
        public String toString() {
            return "LiquidShelf{" +
                    "liquidType='" + liquidType + '\'' +
                    ", liquidCapacity='" + liquidCapacity + '\'' +
                    ", liquidOccupiedCapacity='" + liquidUsedCapacity + '\'' +
                    '}';
        }
}
