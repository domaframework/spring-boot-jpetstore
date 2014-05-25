SELECT
  LI.ORDERID,
  LI.LINENUM,
  LI.ITEMID,
  LI.QUANTITY,
  LI.UNITPRICE,
  I.LISTPRICE,
  I.productName,
  I.attribute1,
  I.attribute2,
  I.attribute3,
  I.attribute4,
  I.attribute5
FROM 
LINEITEM LI LEFT OUTER JOIN (
  select
    I.ITEMID,
    I.LISTPRICE,
    NAME AS productName,
    ATTR1 AS attribute1,
    ATTR2 AS attribute2,
    ATTR3 AS attribute3,
    ATTR4 AS attribute4,
    ATTR5 AS attribute5
  from 
    ITEM I, INVENTORY V, PRODUCT P
  where 
    P.PRODUCTID = I.PRODUCTID
    and 
    I.ITEMID = V.ITEMID
) I 
ON 
  LI.ITEMID = I.ITEMID
WHERE 
  ORDERID = /*orderId*/1