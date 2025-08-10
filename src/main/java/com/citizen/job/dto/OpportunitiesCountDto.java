package com.citizen.job.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OpportunitiesCountDto {
    private Integer totalActiveOpportunities;
    private Integer totalActiveVolunteerOpportunities;
    private Integer totalActiveJobOpportunities;
}
