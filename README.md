# RecyclerView_Divider

you can use it to add an divider to RecyclerView

![default](https://github.com/ChoicesWang/RecyclerView_Divider/blob/master/pictures/screen%20%281%29.png)
![aglie LinearLayoutManager](https://github.com/ChoicesWang/RecyclerView_Divider/blob/master/pictures/screen%20%282%29.png)


----------


![default GridLayoutManager](https://github.com/ChoicesWang/RecyclerView_Divider/blob/master/pictures/screen%20%283%29.png)
![aglie GridLayoutManager](https://github.com/ChoicesWang/RecyclerView_Divider/blob/master/pictures/screen%20%284%29.png)

##Usage

- **Add defualt DividerItemDecoration()**
- 
default DividerItemDecoration is size = 2 and color = Color.GRAY.
```
DividerItemDecoration itemDecoration = new DividerItemDecoration();
mRecyclerView.addItemDecoration(itemDecoration);
```

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
```
- **Add divider for each ItemViewType**
- 
You can also add VerticalDivider or HorizontalDivider for each ItemViewType.

```
class AgileDividerLookup extends DividerItemDecoration.SimpleDividerLookup {

     @Override
        public Divider getVerticalDivider(int position) {
            int type = adapter.getItemViewType(position);
            switch (type) {
                case 0:
                    return new Divider.Builder()
                            .size(2)
                            .color(Color.DKGRAY)
                            .build();
                case 2:
                    return new Divider.Builder()
                            .size(4)
                            .color(Color.GREEN)
                            .build();
                case 3:
                    return new Divider.Builder()
                            .size(10)
                            .color(Color.LTGRAY)
                            .build();
            }
            return null;
        }
 }
```
