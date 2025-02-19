package com.monmi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {
    @Builder.Default
    private int page = 1;   // 보여줄 page 쪽
    @Builder.Default
    private int size = 10;  // 보여줄 page size

    // 검색/필터링 요청 속성들
    private String type; // 검색의 종류(t, c, w, tc, tw, twc)
    private String keyword; // 검색할 문자들

    public String[] getTypes() {
        if (type == null || type.isEmpty()) {
            return null;
        }
        return type.split("");
    }

    public Pageable getPageable(String... props) {   // aa, bb, cc, ...
//        return PageRequest.of(this.page - 1, this.size, Sort.by(props).descending());
        return  PageRequest.of(this.page - 1, this.size, Sort.Direction.DESC, props);
    }

}
