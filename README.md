   
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

>테스트 계정 - ID : police // PW : 112    
>관리자 계정 - ID : admin // PW :team5

중고거래라는 컨셉을 활용하기 위해 회원가입시 주소명을 받고 있습니다.  
화면ui가 아닌 콘솔에서 작동이기때문에 정확하게 주소명을 기입해야 카카오api가 작동한다는 단점이 있습니다.  
카카오 api key를 지운상태입니다. 사용하신다면 MemberServiceImpl 에서 getJSONData 메서드의 String auth에 키를 입력해야 합니다.  

## 구현내용

### Notion <a href="https://jonas-portfolio.notion.site/jonas-portfolio/Final-Spring-Boot-617e1c2a23544c6fa36d6e0a0079bedd">Notion Link</a> <br>
### 담당 : 장바구니(Cart), 구매(Order)
<details>
  <summary>내용</summary>
  <pre>
장바구니 : 파일 영속화를 통해 회원이 담아둔 물품이 file로 유지되어 불러올 수 있음.
상품코드를 입력받아 장바구니에 물품을 담을 수 있음.
재고수량보다 많은 수량을 입력하거나, 장바구니에 담긴 총량이 재고수량을 초과하지 못하도록 설정.
장바구니에 담긴 물품을 확인할 수 있으며, 담긴 물품 삭제 구현.

변동 재고 : 파일 영속화를 통해 장바구니 내역이 유지되었으나, 이로인해 실제 구매가 아님에도 타계정 로그인시 물품 수량이 차감된 상태로 보임.
실제 상품 리스트를 product(변동재고, prod)와 ware(실제 재고)로 구분하였음.

물품구매 및 구매내역 조회: 장바구니에 담긴 물품을 구매 가능
구매가 이루어지면서 당시 구매한 물품별로 결제번호 생성
구매내역조회에서 결제번호와 구매 물품 내역을 확인 가능




  </pre>
</details>




## Document
>#### ppt : 파일첨부
  

  




<!-- Markdown link & img dfn's -->
[npm-image]: https://img.shields.io/npm/v/datadog-metrics.svg?style=flat-square
[npm-url]: https://npmjs.org/package/datadog-metrics
[npm-downloads]: https://img.shields.io/npm/dm/datadog-metrics.svg?style=flat-square
[travis-image]: https://img.shields.io/travis/dbader/node-datadog-metrics/master.svg?style=flat-square
[travis-url]: https://travis-ci.org/dbader/node-datadog-metrics
[wiki]: https://github.com/yourname/yourproject/wiki



