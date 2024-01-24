   
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

- 중고거래라는 컨셉을 활용하기 위해 회원가입시 주소명을 받고 있습니다.  
- 화면ui가 아닌 콘솔에서 작동이기때문에 정확하게 주소명을 기입해야 카카오api가 작동한다는 단점이 있습니다.  
- 카카오 api key를 지운상태입니다.  
  회원가입, 중고거래 게시판 기능을 사용하신다면 MemberServiceImpl 에서 getJSONData 메서드의 String auth에 키를 입력해야 합니다.  
- 프로젝트는 git이 아닌 svn을 통해 진행하였으며, 프로젝트 종료 후 git으로 commit하였습니다. 

>테스트 계정 
- ID : tj // PW : 306
- ID : police // PW : 112
- ID : center // PW : 93 
              
>관리자 계정 
- ID : admin // PW :team5


## 구현내용

### <a href="https://jonas-portfolio.notion.site/jonas-portfolio/Mini-Java-Eclipse-5f3fec9c0d3246f8a35d9f77b9cafa40">Notion Link</a> <br>
### 조원구성  
- 팀장 이동건 : 중고거래, MAIN 메서드 정리
- 팀원 고은채 : 고객 등록 및 관리 구현, 관리자 계정 구현
- 팀원 오상현 : 장바구니, 구매, 주문내역 조회
- 팀원 우성준 : 물류 생성 및 관리, 구매물품 후기 등록


<details>
  <summary>담당역할 내용</summary>
  <pre>

장바구니(Carts) : 
- 파일 영속화를 통해 회원이 담아둔 물품이 file로 유지되어 불러올 수 있습니다.
- 상품코드를 입력받아 장바구니에 물품을 담을 수 있습니다.
- 재고수량보다 많은 수량을 입력하거나, 장바구니에 담긴 총량이 재고수량을 초과하지 못하도록 설정하였습니다.
- 장바구니에 담긴 물품을 확인할 수 있으며, 담긴 물품 삭제 구현했습니다.
- carts.add(new Product(product.getProId(), product.getCategory(), product.getProName(), 
                      proCnt,cntPrice, mem.getLoginUser().getUserId()));  
-> 장바구니에는 물품번호, 물품항목, 물품명, 물품가격, 장바구니에 담은 회원ID(현재 로그인한 회원ID)값을 저장합니다.

회원별 장바구니 : 
- mem = MemberServiceImpl.getInstance(); mem.getLoginUser().getUserId()  
  -> 초기화면에서 로그인한 회원의 ID값을 받아오고 있습니다.
- if (mem.getLoginUser().getUserId().equals(carts.get(i).getWriter()))  
  -> carts List배열을 for반복문으로 배열의 크기만큼 반복하면서 if가정법을 실행합니다.
- 현재 로그인한 회원의 ID와 Carts List배열의 요소를 대조하여 일치하는 값만 제공합니다.
- 장바구니 내역확인 및 장바구니에 담긴 물품을 삭제할 때 또한 로그인 회원과 비교 후 결과값만을 제공합니다.
- 장바구니 물품삭제시, 장바구니에 없는 물품코드를 입력할 경우 "해당물품이 없습니다" 라는 로그와 함께 return됩니다.

물품구매 및 구매내역 조회 :  
- 장바구니에 회원이 추가한 모든 물품이 구매됩니다.
- 장바구니 물품 수정을 원하는 경우, "장바구니 삭제" 기능에서 수정이 가능합니다.
if (mem.equals(cartService.getCarts().get(i).getWriter())) {
    receipts.add(new Order(receipts.size()+1, mem, p.getProName(), p.getProId(),
                           p.getProCnt(), p.getProPrice()));
}  
- 로그인한 회원과 장바구니에 물품을 담은 회원ID가 일치할 경우 구매가 이루어지며, receipt배열에 값이 추가됩니다.
- 추가되는 값은 결제번호, 구매자, 물품명, 물품번호, 구매물품수량, 물품가격 입니다.
- 물품 구매후, carts 배열 초기화가 진행됩니다.
for(int i = 0; i < cartService.getCarts().size(); i++) {
    Product p = cartService.getCarts().get(i);
    if (Id.equals(cartService.getCarts().get(i).getWriter())) {
        cartService.getCarts().remove(i);
    }
}
- clear(); 메서드를 처음에 활용해보았으나, carts배열이 전부 초기화 되면서
 다른사람의 장바구니까지 삭제되는 문제가 발생했습니다.
- carts배열의 크기만큼 반복문을 실행하며, 이때 현재로그인한 회원의 ID값을 갖고있는 배열요소가 나오면
 삭제remove(i)하여, 장바구니 내의 물품을 초기화 하는 방식으로 해결했습니다.
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



