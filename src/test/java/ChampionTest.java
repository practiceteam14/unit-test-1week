import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.*;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class ChampionTest {
    private List<Champion> championList = new ArrayList<Champion>();

    @Before
    public void setUp() {

        //5개의 챔피언 객체를 만듭니다.
        Champion topChamp = new Champion("다리우스", "탑");
        Champion jungleChamp = new Champion("리신", "정글");
        Champion midChamp = new Champion("르블랑", "미드");
        Champion adcChamp = new Champion("베인", "바텀");
        Champion supportChamp = new Champion("레오나", "바텀");

        //앞서 만든 List 에 각 챔피언을 추가합니다.
        championList.add(topChamp);
        championList.add(jungleChamp);
        championList.add(midChamp);
        championList.add(adcChamp);
        championList.add(supportChamp);
    }

    //List<String>을 생성하고 값이 비어 있는지를 테스트 empty() - 수정: 김영진
    @Test
    public void givenCollectionWhenEmptyCorrect() {
        List<String> emptyList = new ArrayList<>();
        //assertThat(emptyList, empty());
        assertTrue(emptyList.isEmpty());  // assertTrue 사용해서 변화줌
    }

    //notNullValue 활용한 테스트 - 수정: 김영진
    @Test
    public void notNullCheck() {
        String lck = "LCK won the rift rivals!";  //문자열 수정
        assertThat(lck, notNullValue());
    }

    //nullValue 활용한 테스트 - 수정: 김영진
    @Test
    public void givenStringWhenNullIsCorrect() {
        String lck = null;
        //assertThat(lck, nullValue());
        assertThat(lck, is(nullValue()));  // is 사용해도 같은 결과인지 확인
    }


    //문자열 관련 테스트 anyOf, containsString, endWith - 수정: 김수진
    @Test
    public void testForRelatedString() {
        String sampleString1 = "Player 1 Focus";
        String sampleString2 = "Player point";
        String startString = "Player";
        String endString = "point";

        // anyOf 사용하여 테스트
        assertThat(sampleString1, anyOf(startsWith(startString), containsString(endString)));
        // allOf 사용하여 테스트
        assertThat(sampleString2, allOf(startsWith(startString), containsString(endString)));
        // not 사용하여 테스트
        assertThat(sampleString1, not(startsWith("Players")));
        // is 사용하여 테스트
        assertThat(sampleString2, is(endsWith(endString)));
    }

    //부동소수점 범위 closeTo 테스트 - 수정: 김수진
    @Test
    public void testForFloatingPoint() {
        assertThat(3.14, closeTo(3, 0.2));
        assertThat(31.4, closeTo(31, 0.5));
        assertThat(314.0, closeTo(300, 20));
    }

    //anything 테스트 - 수정: 김수진
    @Test
    public void shouldNotErrorGetReference() {
        assertThat(championList.get(2), anything());

        // championList의 크기를 벗어나 불러올 수 없는 값을 가져오려 할 때, 실패하는지 테스트
        System.out.println(championList.size());
        // assertThat(championList.get(7), anything());

        // 불러올 수 있는 범위이면 항상 성공
        assertThat(championList.get(0), anything());
        assertThat(championList.get(1), anything());
        assertThat(championList.get(3), anything());
        assertThat(championList.get(4), anything());
    }

    //객체 크기 검증 테스트 hasSize - 수정 전병재
    @Test
    public void shouldChampionCountFive() {
        assertThat(championList.size(),equalTo(5)); //원래는 5명
        Champion newSupChamp= new Champion("스웨인","바텀");
        championList.add(newSupChamp);  // 챔피언 하나 추가
        assertThat(championList.size(),is(6)); //추가했더니 6명
        championList.remove(newSupChamp); //챔피언 다시 제거
        assertThat(championList,hasSize(5)); //제거 했더니 다시 5명
//        assertTrue(championList.size() == 5);
//        assertThat(championList.size(), is(5));
//        assertThat(championList, hasSize(5));
    }

    //서폿 챔피언은 타릭이어야 한다라는 조건으로 테스트 코드 작성 - 수정 장성호
    @Test
    public void shouldSupportChampionIsTaric() {
        Champion supportChamp = new Champion("타릭", "바텀");
        assertThat("바텀", is(equalTo(championList.get(4).getPosition())));
        assertThat("타릭", equalTo(championList.get(4).getName()));
//        assertThat("타릭", is(supportChamp.getName()));
//        assertThat("타릭", is(equalTo(supportChamp.getName())));
//        assertThat("타릭", equalTo(supportChamp.getName()));
    }

    //hasProperty 활용하여 속성이 포함되어 있는지 테스트 - 수정 장성호
    @Test
    public void shouldHasPropertyPosition() {
        // create new mid Champion
        Champion midChamp = new Champion("빅토르", "미드");
        // But This Champion go any position
        midChamp.setPosition("미드, 탑, 바텀");
        assertThat(midChamp, hasProperty("position"));
        assertThat(midChamp, hasProperty("position", equalTo("미드")));
//        assertThat(championList.get(0), hasProperty("position"));
//        assertThat(championList.get(0), hasProperty("position", equalTo("탑")));
    }

    //hasToString 활용 테스트 - 수정 장성호
    @Test
    public void shouldHaveSomeChampName() {
        List<String> champListNames = Arrays.asList("루시안", "나미", "렉사이", "갈리오", "모르가느", "나서스", "나르");
        for (String champListName : champListNames) assertThat(champListName.charAt(0), hasToString("나"));
//        assertThat(champListNames.get(0), hasToString("루시안"));
    }

    //property와 value가 같은지 테스트 - 수정 전병재
    @Test
    public void shouldHaveSamePropertyAndValue() {
        List<String> championNames1 = Arrays.asList("루시안", "애쉬", "렉사이", "갈리오", "모르가나", "블라디미르");
        List<String> championNames2 = Arrays.asList("루시안", "애쉬", "렉사이", "갈리오", "모르가나", "블라디미르");
        assertEquals(championNames1,championNames2);// assertEquals 사용
        assertThat(championNames1,samePropertyValuesAs(championNames2));
//        assertThat(championNames1, samePropertyValuesAs(championNames2));
    }

    //바텀 첫번째 챔피언은 베인이여야 한다라는 조건으로 테스트 코드 작성, stream 활용예 - 수정 전병재
    @Test
    public void shouldTopChampionIsVayne() {
        Optional<Champion> selectedChampion = championList.stream()
                .filter(c->c.getPosition().equals("바텀"))
                .findFirst();
        String champ=selectedChampion.get().getName();
        assertThat(champ,is("베인")); // Position이 바텀인 두 챔피언중 첫번째인 베인만 찾음

//        Optional<Champion> filterdChampion = championList.stream()
//                .filter(c -> c.getPosition().equals("탑"))
//                .findFirst();
//        String champName = filterdChampion.get().getName();
//        assertTrue(champName.equals("다리우스"));
//        assertThat("다리우스", is(champName));
    }
    
    //공백을 제거한 상태에서도 두 값이 같은지 비교 - 김영진
    @Test
    public void testForWhiteSpace(){
        String testString1 = "문도박사";
        String testString2 = " 문도박사";

        assertThat(testString1, equalToIgnoringWhiteSpace(testString2));

    }
    //같은 타입의 값이 사용 되었는지 - 김영진
    @Test
    public void testSameType(){

        assertThat(championList,instanceOf(ArrayList.class));
    }

    // 대소문자가 달라도 같은 것으로 판단하여 비교 - 김수진
    @Test
    public void equalToIgnoringCaseTest() {
        String testString1 = "AbCdEfG";
        String testString2 = "aBcDeFg";

        assertThat(testString1, equalToIgnoringCase(testString2));
    }

    // @Ignore로 인해 테스트를 진행하지 않는지 확인 - 김수진
    @Ignore
    @Test
    public void IgnoreTest(){
        System.out.println("이 테스트는 무시한다.");
    }

    // 같은 인스턴스인지 테스트 - 김수진
    @Test
    public void sameInstanceTest() {
        assertThat("Test", sameInstance("Test"));
        assertThat("Test", not(sameInstance("Not Same Test")));
    }

    // hasItem 및 hasItems 테스트 - 김수진
    @Test
    public void hasItemTest() {
        assertThat(Arrays.asList("모르가나", "애쉬", "갈리오"), hasItem("모르가나"));
        assertThat(Arrays.asList("모르가나", "애쉬", "갈리오"), hasItems("모르가나", "애쉬"));
    }

}