package UIComponent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
public class demodata implements TableModel{

        private String [] title = {"周一","周二","周三","周四","周五","周六","周日"};
        private String [][] data = new String [8][7];

        public demodata() {
            for(int i=0;i<data.length;i++) {
                for(int j=0;j<data[i].length;j++) {
                    data[i][j]="";
                }
            }
        }
        @Override
        public int getRowCount() {//8行
            // TODO Auto-generated method stub
            return 8;
        }

        @Override
        public int getColumnCount() {//7列
            // TODO Auto-generated method stub
            return 7;
        }

        @Override
        public String getColumnName(int arg0) {//表头

            return title[arg0];
        }

        @Override
        public Class<?> getColumnClass(int arg0) {
            // TODO Auto-generated method stub
            return String.class;
        }

        @Override
        public boolean isCellEditable(int arg0, int arg1) {
            // TODO Auto-generated method stub
            return true;
        }

        @Override
        public Object getValueAt(int arg0, int arg1) {
            // TODO Auto-generated method stub
            return data[arg0][arg1];
        }

        @Override
        public void setValueAt(Object arg0, int arg1, int arg2) {//往里面送一个值
            data[arg1][arg2]=(String)arg0;
        }

        @Override
        public void addTableModelListener(TableModelListener l) {
            // TODO Auto-generated method stub

        }

        @Override
        public void removeTableModelListener(TableModelListener l) {
            // TODO Auto-generated method stub

        }



}
