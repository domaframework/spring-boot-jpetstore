SELECT
  PRODUCTID,
  NAME,
  DESCN,
  CATEGORY
FROM 
  PRODUCT
WHERE
/*%for keyword : keywords */
  lower(name) like /* @infix(keyword.toLowerCase()) */'a' 
  OR 
  lower(category) like /* @infix(keyword.toLowerCase()) */'a' 
  OR 
  lower(descn) like /* @infix(keyword.toLowerCase()) */'a'
  /*%if keyword_has_next */
  /*# "OR" */
  /*%end*/
/*%end*/
