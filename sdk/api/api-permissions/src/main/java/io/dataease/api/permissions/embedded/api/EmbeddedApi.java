package io.dataease.api.permissions.embedded.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.dataease.api.permissions.embedded.dto.EmbeddedCreator;
import io.dataease.api.permissions.embedded.dto.EmbeddedEditor;
import io.dataease.api.permissions.embedded.dto.EmbeddedOrigin;
import io.dataease.api.permissions.embedded.dto.EmbeddedResetRequest;
import io.dataease.api.permissions.embedded.vo.EmbeddedGridVO;
import io.dataease.license.config.XpackResource;
import io.dataease.model.KeywordRequest;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Tag(name = "嵌入式")
@ApiSupport(order = 883, author = "fit2cloud-someone")
@XpackResource
public interface EmbeddedApi {

    @Operation(summary = "查询")
    @ApiOperationSupport(order = 1)
    @PostMapping("/pager/{goPage}/{pageSize}")
    IPage<EmbeddedGridVO> queryGrid(@PathVariable("goPage") int goPage, @PathVariable("pageSize") int pageSize, @RequestBody KeywordRequest request);

    @Operation(summary = "创建")
    @ApiOperationSupport(order = 2)
    @PostMapping("/create")
    void create(@RequestBody EmbeddedCreator creator);

    @Operation(summary = "编辑")
    @ApiOperationSupport(order = 3)
    @PostMapping("/edit")
    void edit(@RequestBody EmbeddedEditor editor);

    @Operation(summary = "删除")
    @ApiOperationSupport(order = 4)
    @Parameter(name = "id", description = "ID", required = true, in = ParameterIn.PATH)
    @PostMapping("/delete/{id}")
    void delete(@PathVariable("id") Long id);

    @Operation(summary = "批量删除")
    @ApiOperationSupport(order = 4)
    @PostMapping("/batchDelete")
    void batchDelete(@RequestBody List<Long> ids);

    @ApiOperationSupport(order = 5)
    @Operation(summary = "重置密钥")
    @PostMapping("/reset")
    void reset(@RequestBody EmbeddedResetRequest request);

    @ApiOperationSupport(order = 6)
    @Operation(summary = "嵌入式应用域名集合", hidden = true)
    @GetMapping("/domainList")
    List<String> domainList();

    @Hidden
    @PostMapping("/initIframe")
    void initIframe(@RequestBody EmbeddedOrigin origin);

    @ApiOperationSupport(order = 7)
    @Operation(summary = "获取Token参数")
    @GetMapping("/getTokenArgs")
    Map<String, Object> getTokenArgs();

    @Hidden
    @GetMapping("/limitCount")
    int getLimitCount();
}
