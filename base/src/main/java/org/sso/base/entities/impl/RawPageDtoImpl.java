package org.sso.base.entities.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.sso.base.entities.RawDto;
import org.sso.base.entities.RawPageDto;

import java.util.ArrayList;
import java.util.List;

public class RawPageDtoImpl<T extends RawDto> implements RawPageDto<T> {
    @JsonProperty("page")
    protected int page = 0;

    @JsonProperty("size")
    protected int size = 10;

    @JsonProperty("total")
    protected long total = 0;

    @JsonProperty("count")
    protected boolean count = true;

    @JsonProperty("content")
    protected List<T> content = new ArrayList<>();

    public RawPageDtoImpl(int page, int size, long total, boolean count, List<T> content) {
        this.page = page;
        this.size = size;
        this.total = total;
        this.count = count;
        this.content = content;
    }
}
