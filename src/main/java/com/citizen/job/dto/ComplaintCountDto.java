package com.citizen.job.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintCountDto {
    Integer totalComplaintsCount;
    Integer pendingComplaintsCount;
    Integer underReviewComplaintsCount;
    Integer assignedComplaintsCount;
    Integer inProgressComplaintsCount;
    Integer onHoldComplaintsCount;
    Integer resolvedComplaintsCount;
    Integer rejectedComplaintsCount;
}
