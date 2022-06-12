package com.application.newsnow.enums;

public enum Link {

    LINK_ARTS("https://static01.nyt.com/images/2022/06/10/arts/10morgan-library-4/10morgan-library-4-superJumbo.jpg"),
    LINK_BUSSINESS("https://static01.nyt.com/images/2022/06/09/business/09gas-prices/merlin_207604263_c3958cbc-fc17-4a27-b1fb-7def28764b79-superJumbo.jpg"),
    LINK_AUTO("https://static01.nyt.com/images/2022/06/09/business/09tesla-investigation/merlin_191570271_a59e3f34-b0b4-4bd5-bed9-126b5b83e21a-superJumbo.jpg"),
    LINK_FASHION("https://static01.nyt.com/images/2022/06/12/fashion/09currywalk1/merlin_208298322_543500e6-ce73-431c-a17d-385388a26932-superJumbo.jpg"),
    LINK_FOOD("https://static01.nyt.com/images/2022/05/27/dining/27Tanis1/merlin_207073785_5779025c-b577-4080-91ef-16a69bf2bbcd-superJumbo.jpg"),
    LINK_OPINION("https://static01.nyt.com/images/2022/05/15/opinion/15paul1/merlin_195541311_3033931e-b1e3-4f9b-9327-9b46645ed91c-superJumbo.jpg"),
    LINK_TECHNOLOGY("https://static01.nyt.com/images/2022/06/07/multimedia/06xp-taserdrone-print/06xp-taserdrone-superJumbo.jpg"),
    LINK_REALESTATE("https://static01.nyt.com/images/2022/06/12/realestate/08IHH-Toronto-slide-CN99/08IHH-Toronto-slide-CN99-superJumbo.jpg"),
    LINK_MOVIES("https://static01.nyt.com/images/2022/06/09/multimedia/09jurassic1/09jurassic1-superJumbo.jpg"),
    LINK_SPORTS("https://static01.nyt.com/images/2022/06/09/multimedia/09tennis-atp/09tennis-atp-superJumbo.jpg");

    private String link;

    Link(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
