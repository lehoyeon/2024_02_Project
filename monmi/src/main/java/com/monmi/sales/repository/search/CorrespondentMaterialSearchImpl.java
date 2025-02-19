package com.monmi.sales.repository.search;

import com.monmi.sales.domain.CorrespondentMaterial;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CorrespondentMaterialSearchImpl implements CorrespondentMaterialSearch {
    private final EntityManager entityManager;

    @Override
    public Page<CorrespondentMaterial> correspondent_material_search_all(String[] types, String keyword, Pageable pageable) {
        // JPQL로 작성
        StringBuilder jpql = new StringBuilder("SELECT b FROM CorrespondentMaterial b WHERE b.companyName IS NOT NULL");

        // 동적 검색 조건 추가
        if ((types != null && types.length > 0) && keyword != null) {
            jpql.append(" AND (");

            for (int i = 0; i < types.length; i++) {
                String type = types[i];
                switch (type) {
                    case "t":
                        jpql.append("b.companyTel LIKE :keyword");
                        break;
                    case "n":
                        jpql.append("b.companyName LIKE :keyword");
                        break;
                    case "m":
                        jpql.append("b.material LIKE :keyword");
                        break;
                    case "a":
                        jpql.append("b.companyAddress LIKE :keyword");
                        break;
                }

                // 각 조건 사이에 "OR" 추가
                if (i < types.length - 1) {
                    jpql.append(" OR ");
                }
            }
            jpql.append(")");
        }
        jpql.append(" ORDER BY companyName ASC");

        // JPQL로 쿼리 생성
        TypedQuery<CorrespondentMaterial> query = entityManager.createQuery(jpql.toString(), CorrespondentMaterial.class);
        TypedQuery<Long> countQuery = entityManager.createQuery(
                jpql.toString().replace("SELECT b", "SELECT COUNT(b)"), Long.class
        );

        // 파라미터 바인딩
        if ((types != null && types.length > 0) && keyword != null) {
            query.setParameter("keyword", "%" + keyword + "%");
            countQuery.setParameter("keyword", "%" + keyword + "%");
        }

        // 결과 조회
        List<CorrespondentMaterial> list = query.getResultList();
        long count = countQuery.getSingleResult();

        return new PageImpl<>(list, pageable, count);
    }
}
