select
  I.ITEMID,
  I.LISTPRICE,
  I.UNITCOST,
  I.SUPPLIER,
  I.PRODUCTID,
  NAME AS productName,
  DESCN AS productDescription,
  CATEGORY AS productCategoryId,
  STATUS,
  ATTR1,
  ATTR2,
  ATTR3,
  ATTR4,
  ATTR5,
  QTY
from 
  ITEM I, 
  INVENTORY V, 
  PRODUCT P
where 
  P.PRODUCTID = I.PRODUCTID
  and 
  I.ITEMID = V.ITEMID
  and 
  I.ITEMID = /*itemId*/'EST-2'