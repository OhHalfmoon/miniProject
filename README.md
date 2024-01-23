   
# 프로젝트명

> team5
> project : 당근사  
> 주제 : 파일영속성 및 싱글톤을 활용한 JAVA콘솔 위치기반 중고거래 쇼핑몰 구현 

[![NPM Version][npm-image]][npm-url]
[![Build Status][travis-image]][travis-url]
[![Downloads Stats][npm-downloads]][npm-url]



![](../header.png)

## 개발 환경 설정

>Tools : Eclipse, JAVA SE-16, SVN, KAKAO MAP API
  

## 사용 예제

>테스트 계정 
- ID : tj // PW : 306
- ID : police // PW : 112
- ID : center // PW : 93 
              
>관리자 계정 
- ID : admin // PW :team5

- 중고거래라는 컨셉을 활용하기 위해 회원가입시 주소명을 받고 있습니다.  
- 화면ui가 아닌 콘솔에서 작동이기때문에 정확하게 주소명을 기입해야 카카오api가 작동한다는 단점이 있습니다.  
- 카카오 api key를 지운상태입니다. 
  회원가입, 중고거래 게시판 기능을 사용하신다면 MemberServiceImpl 에서 getJSONData 메서드의 String auth에 키를 입력해야 합니다.  
- 프로젝트는 git이 아닌 svn을 통해 진행하였으며, 프로젝트 종료 후 git으로 commit하였습니다. 

## 구현내용

### <a href="https://jonas-portfolio.notion.site/jonas-portfolio/Final-Spring-Boot-617e1c2a23544c6fa36d6e0a0079bedd">Notion Link</a> <br>
### 담당 : 장바구니(Cart), 구매(Order)
<details>
  <summary>내용</summary>
  <pre>

장바구니(Carts) : 
- 파일 영속화를 통해 회원이 담아둔 물품이 file로 유지되어 불러올 수 있습니다.
- 상품코드를 입력받아 장바구니에 물품을 담을 수 있습니다.
- 재고수량보다 많은 수량을 입력하거나, 장바구니에 담긴 총량이 재고수량을 초과하지 못하도록 설정하였습니다.
- 장바구니에 담긴 물품을 확인할 수 있으며, 담긴 물품 삭제 구현했습니다.
- carts.add(new Product(product.getProId(), product.getCategory(), product.getProName(), proCnt,cntPrice, mem.getLoginUser().getUserId()));
- 장바구니에는 물품번호, 물품항목, 물품명, 물품가격, 장바구니에 담은 회원ID(현재 로그인한 회원ID)값을 저장합니다.

회원별 장바구니 : 
- mem = MemberServiceImpl.getInstance(); mem.getLoginUser().getUserId() : 초기화면에서 로그인한 회원의 ID값을 받아오고 있습니다.
- if (mem.getLoginUser().getUserId().equals(carts.get(i).getWriter())) : carts List배열을 for반복문으로 배열의 크기만큼 반복하면서 if가정법을 실행합니다.
- 현재 로그인한 회원의 ID와 Carts List배열의 요소를 대조하여 일치하는 값만 제공합니다.
- 장바구니 내역확인 및 장바구니에 담긴 물품을 삭제할 때 또한 로그인 회원과 비교 후 결과값만을 제공합니다.
- 장바구니 물품삭제시, 나의 장바구니에 없는 물품의 코드를 입력할 경우 "해당물품이 없습니다" 라는 로그와 함께 return됩니다.

물품구매 및 구매내역 조회: 
- 장바구니에 담긴 물품을 구매합니다
- 구매가 이루어지면서 당시 구매한 물품별로 결제번호가 생성됩니다.
- 구매내역조회에서 내가 구매했던 결제번호와 구매 물품 내역을 확인할 수 있습니다.




  </pre>
</details>




## Document
>#### ppt : 파일첨부
>#### pdf : 파일첨부
  

  




<!-- Markdown link & img dfn's -->
[npm-image]: https://img.shields.io/npm/v/datadog-metrics.svg?style=flat-square
[npm-url]: https://npmjs.org/package/datadog-metrics
[npm-downloads]: https://img.shields.io/npm/dm/datadog-metrics.svg?style=flat-square
[travis-image]: https://img.shields.io/travis/dbader/node-datadog-metrics/master.svg?style=flat-square
[travis-url]: https://travis-ci.org/dbader/node-datadog-metrics
[wiki]: https://github.com/yourname/yourproject/wiki



