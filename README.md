# RecyclerView_Divider

you can use it to add an divider to RecyclerView

- **Add defualt DividerItemDecoration()**
- 
default DividerItemDecoration is size = 2 and color = Color.GRAY.
```
DividerItemDecoration itemDecoration = new DividerItemDecoration();
mRecyclerView.addItemDecoration(itemDecoration);
```
such as

- **Add Agile ItemDecoration for each item**
- 
For each item，you can add agile divider , You can choose vertical or horizontal,If mRecyclerView 
has mLinearLayoutManager,only need to Override one method of getVerticalDivider() and getHorizontalDivider(). if mRecyclerView used mGridLayoutManager, you must Override both of them.
```
DividerItemDecoration itemDecoration = new DividerItemDecoration();
itemDecoration.setDividerLookup(new AgileDividerLookup());
mRecyclerView.addItemDecoration(itemDecoration);

class AgileDividerLookup extends DividerItemDecoration.SimpleDividerLookup {

     @Override
     public Divider getVerticalDivider(int position) {
          return super.getVerticalDivider(position);
     }
     
     @Override
     public Divider getHorizontalDivider(int position) {
         return super.getHorizontalDivider(position);
     }
 }
such as：
```
